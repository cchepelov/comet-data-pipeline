package com.ebiznext.comet.job

import java.io.InputStream

import com.ebiznext.comet.config.DatasetArea
import com.ebiznext.comet.data.Data
import com.ebiznext.comet.schema.handlers.{HdfsStorageHandler, LaunchHandler, SchemaHandler}
import com.ebiznext.comet.schema.model.SchemaModel
import com.ebiznext.comet.workflow.DatasetValidator
import org.apache.hadoop.fs.Path
import org.json4s.native.Serialization.{write => jswrite}

object Main extends Data {
  def main(args: Array[String]) = {
    implicit val formats = SchemaModel.formats
    val storageHandler = new HdfsStorageHandler
    val schemaHandler = new SchemaHandler(storageHandler)

    DatasetArea.init(storageHandler)

    val sh = new HdfsStorageHandler
    val domainsPath = new Path(DatasetArea.domains, domain.name + ".json")
    sh.write(jswrite(domain), domainsPath)
    val typesPath = new Path(DatasetArea.types, "types.json")
    sh.write(jswrite(types), typesPath)

    DatasetArea.initDomains(storageHandler, schemaHandler.domains.map(_.name))

    val stream: InputStream = getClass.getResourceAsStream("/SCHEMA-VALID.dsv")
    val lines = scala.io.Source.fromInputStream(stream).getLines().mkString("\n")
    val targetPath = DatasetArea.path(DatasetArea.pending("DOMAIN"), "SCHEMA-VALID.dsv")
    storageHandler.write(lines, targetPath)
    val validator = new DatasetValidator(storageHandler, schemaHandler, new LaunchHandler {
      override def launch(domain: String, schema: String, path: Path): Boolean = ???
    })
    validator.run()

  }

}