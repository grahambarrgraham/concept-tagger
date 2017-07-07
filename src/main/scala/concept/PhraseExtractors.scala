package concept

import java.io.InputStream

import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.{Parse, Parser, ParserFactory, ParserModel}


object OpenNlpNounPhraseExtractor {
  val parser: Parser =
    ParserFactory.create(new ParserModel(getClass.getClassLoader.getResourceAsStream("en-parser-chunking.bin")))
}

class OpenNlpNounPhraseExtractor extends PhraseExtractor {
  import OpenNlpNounPhraseExtractor._

  private val NounPhrase = "NP"

  def extractNounPhrases(sentence: String): Seq[String] = traverseParseTree(parseLine(sentence))

  //@tailrec
  private def traverseParseTree(parses: List[Parse]): List[String] = parses match {
    case (head: Parse) :: tail if head.getType == NounPhrase =>
      head.getCoveredText :: traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case (head: Parse) :: tail =>
      traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case Nil => List.empty
  }

  private def parseLine(sentence: String) = {
    val TopNParses = 1
    ParserTool.parseLine(sentence, parser, TopNParses).toList
  }

}

class TrivialPhraseExtractor extends PhraseExtractor {
  def extractNounPhrases(sentence: String): Seq[String] = Seq(sentence)
}