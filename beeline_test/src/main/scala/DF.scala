import beeline.spark
import shemas.{customerschema, orderschema, productschema}

object DF {
  val orderDF=spark.read.format("csv")
    .option("header", "false")
    .option("delimiter", "\t")
    .schema(orderschema)
    .load("src/resourses/order.csv")

  val customerDF=spark.read.format("csv")
    .option("header", "false")
    .option("delimiter", "\t")
    .schema(customerschema)
    .load("src/resourses/customer.csv")

  val productDF=spark.read.format("csv")
    .option("header", "false")
    .option("delimiter", "\t")
    .schema(productschema)
    .load("src/resourses/product.csv")

}
