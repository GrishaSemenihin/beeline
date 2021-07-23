import DF.orderDF
import DF.productDF
import DF.customerDF

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.{Dataset, DataFrame, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._



object beeline extends App {
  val spark = SparkSession.builder().
    appName("beeline_test").
    config("spark.master", "local").
    getOrCreate()

  spark.conf.set("spark.sql.autoBroadcastJoinThreshold","-1")

  val context: SparkContext = spark.sparkContext

  val CommonActionsDF=orderDF.join(customerDF,orderDF("customerID")===customerDF("id"))
                           .join(productDF,orderDF("productID")===productDF("id"))
                           .select("order_status","customer_name","product_name","numberOfProduct")
                           .filter("order_status = 'delivered'")

  val SumDF=CommonActionsDF
                            .groupBy("customer_name","product_name").agg(sum("numberOfProduct"))


  val MaxDF=CommonActionsDF
                            .groupBy("customer_name","product_name").agg(sum("numberOfProduct"))
                            .groupBy("customer_name").agg(max("sum(numberOfProduct)"))
                            .withColumnRenamed("customer_name","customer_name_final")


  val finalDF=SumDF.join(MaxDF,SumDF("customer_name")===MaxDF("customer_name_final"))
                            .filter(col("sum(numberOfProduct)") === col("max(sum(numberOfProduct))"))
                            .select("customer_name","product_name")
                            .groupBy("customer_name").agg(collect_list("product_name"))
                            .withColumn("product_name",col("collect_list(product_name)").cast(StringType))
                            .drop(col("collect_list(product_name)"))
                            .coalesce(1)
                            .write.format("csv").save("src/resourses/result.csv")

}