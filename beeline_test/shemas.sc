import org.apache.spark.sql.types.{StringType, StructType}

val orderschema = new StructType()
  .add("customerID",StringType)
  .add("orderID",StringType)
  .add("productID",StringType)
  .add("numberOfProduct",StringType)
  .add("orderDate",StringType)
  .add("status",StringType)


val customerschema = new StructType()
  .add("id",StringType)
  .add("name",StringType)
  .add("email",StringType)
  .add("joinDate",StringType)
  .add("status",StringType)