package concept.extractors

import concept.PhraseExtractor
import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.{Parse, Parser, ParserFactory, ParserModel}


object TrivialNlpNounPhraseExtractor {
  val parser: Parser =
    ParserFactory.create(new ParserModel(getClass.getClassLoader.getResourceAsStream("en-parser-chunking.bin")))
}

class TrivialNlpNounPhraseExtractor extends PhraseExtractor {
  import TrivialNlpNounPhraseExtractor._

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

