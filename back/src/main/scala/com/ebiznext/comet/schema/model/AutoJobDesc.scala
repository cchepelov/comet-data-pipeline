package com.ebiznext.comet.schema.model

import com.ebiznext.comet.config.HiveArea

/**
  *
  * @param sql     SQL request to exexute (do not forget to prefix table names with the database name
  * @param domain  Output domain in Business Area (Will be the Database name in Hive)
  * @param dataset Dataset Name in Business Area (Will be the Table name in Hive)
  * @param write   Append to or overwrite existing data
  */
case class AutoTask(sql: String, domain: String, dataset: String, write: Write, partition: List[String],
                    presql: Option[List[String]], postsql: Option[List[String]], area: Option[HiveArea] = None)

/**
  *
  * @param name  Buisiness Job logical name
  * @param tasks List of business tasks to execute
  */
case class AutoJobDesc(name: String, tasks: List[AutoTask], area: Option[HiveArea] = None) {
  def getArea() = area.getOrElse(HiveArea.business)
}
