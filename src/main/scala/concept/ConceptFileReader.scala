package concept

import scala.io.{BufferedSource, Source}
import scala.util.Try
import scala.language.reflectiveCalls

/**
  * Created by graham on 08/07/2017.
  */
object ConceptFileReader {

  def readLines(file: String): Try[List[String]] =
    Try {
      using(getSourceFromClasspath(file)) { source =>
        (for (line <- source.getLines) yield line).toList
      }
    }

  private def getSourceFromClasspath(file: String): BufferedSource = {
    Source.fromInputStream(getClass.getClassLoader.getResourceAsStream(file))
  }

  private def using[A, B <: {def close(): Unit}] (closeable: B) (f: B => A): A =
    try { f(closeable) } finally { closeable.close() }
}
