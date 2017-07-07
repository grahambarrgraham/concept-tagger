package concept

import java.io.InputStream

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.{Parse, Parser, ParserFactory, ParserModel}


trait NounPhraseExtractor {
  def extract(sentence: String): Seq[String]
}

object OpenNlpNounPhraseExtractor {
  val TopNParses = 1
  private val asStream: InputStream = getClass.getClassLoader.getResourceAsStream("en-parser-chunking.bin")
  val parser: Parser = ParserFactory.create(new ParserModel(asStream))
}

class OpenNlpNounPhraseExtractor extends NounPhraseExtractor {

  import OpenNlpNounPhraseExtractor._

  def extract(sentence: String): Seq[String] = traverseParseTree(parseLine(sentence))

  //@tailrec
  private def traverseParseTree(parses: List[Parse]): List[String] = parses match {
    case (head: Parse) :: tail if head.getType == "NP" =>
      head.getCoveredText :: traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case (head: Parse) :: tail =>
      traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case Nil => List.empty
  }

  private def parseLine(sentence: String) = {
    ParserTool.parseLine(sentence, parser, TopNParses).toList
  }

}

