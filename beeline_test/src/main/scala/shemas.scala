import org.apache.spark.sql.types.{StringType, StructType,IntegerType}

object shemas {
  val orderschema = new StructType()
    .add("customerID",StringType)
    .add("orderID",StringType)
    .add("productID",StringType)
    .add("numberOfProduct",IntegerType)
    .add("orderDate",StringType)
    .add("order_status",StringType)


  val customerschema = new StructType()
    .add("id",StringType)
    .add("customer_name",StringType)
    .add("email",StringType)
    .add("joinDate",StringType)
    .add("customer_status",StringType)

  val productschema = new StructType()
    .add("id",StringType)
    .add("product_name",StringType)
    .add("price",StringType)
    .add("numberOfProducts",StringType)
}
