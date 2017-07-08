package concept.extractors

import concept.PhraseExtractor
import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.{Parse, Parser, ParserFactory, ParserModel}


object TrivialNlpNounPhraseExtractor {
  private val model: ParserModel = new ParserModel(getClass.getClassLoader.getResourceAsStream("en-parser-chunking.bin"))
  def parser: Parser = ParserFactory.create(model)
}

class TrivialNlpNounPhraseExtractor extends PhraseExtractor {
  import TrivialNlpNounPhraseExtractor._

  private val NounPhrase = "NP"

  def extractNounPhrases(sentence: String): Set[String] =
    traverseParseTree(parseLine(sentence))
      .map{p => p.show(); p}
      .map(p => p.getCoveredText)
      .toSet
      .filterNot(p => Seq("i", "me", sentence.toLowerCase).contains(p.toLowerCase))

  //@tailrec
  private def traverseParseTree(parses: List[Parse]): List[Parse] = parses match {
    case (head: Parse) :: tail if head.getType == NounPhrase =>
      head :: traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case (head: Parse) :: tail =>
      traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case Nil => List.empty
  }

  private def parseLine(sentence: String) = {
    val TopnParses = 1
    ParserTool.parseLine(sentence, parser, TopnParses).toList
  }

}

