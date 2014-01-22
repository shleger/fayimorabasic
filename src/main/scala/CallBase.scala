import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Get, Put, HTable, HBaseAdmin}
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.rest.Constants
import org.apache.hadoop.hbase.util.Bytes

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $    192.168.65.128:2181
 */
object CallBase extends App{
  println("Hello World 22!!!")

//  var config: Configuration = new Configuration()
//  config.set("hbase.master","192.168.65.128:2181")

  val conf = HBaseConfiguration.create()

  conf.set("hbase.zookeeper.quorum","192.168.65.128")
  conf.set("hbase.zookeeper.property.clientPort","2181")



  val admin = new HBaseAdmin(conf)

  // list the tables
  val listtables=admin.listTables()
  listtables.foreach(println)

  println("list tables completed")

  // let's insert some data in 'mytable' and get the row

  val table = new HTable(conf, "test")

  val theput= new Put(Bytes.toBytes("rowkey1"))

  theput.add(Bytes.toBytes("data"),Bytes.toBytes("id1"),Bytes.toBytes("one"))
  table.put(theput)

  println("put completed")

  val theget= new Get(Bytes.toBytes("rowkey1"))
  val result=table.get(theget)
  val value=result.value()
  println(Bytes.toString(value))

  println("get completed")

}
