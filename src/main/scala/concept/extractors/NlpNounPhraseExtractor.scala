package concept.extractors

import concept.PhraseExtractor
import opennlp.tools.cmdline.parser.ParserTool
import opennlp.tools.parser.{Parse, Parser, ParserFactory, ParserModel}


object NlpNounPhraseExtractor {
  private val model: ParserModel = new ParserModel(getClass.getClassLoader.getResourceAsStream("en-parser-chunking.bin"))
  def parser: Parser = ParserFactory.create(model)
  private val wordAndWordPairExtractor: WordAndWordPairExtractor = new WordAndWordPairExtractor
}

class NlpNounPhraseExtractor extends PhraseExtractor {
  import NlpNounPhraseExtractor._

  private val NounPhrase = "NP"

  def extractPhrases(sentence: String): Set[String] =
    traverseParseTree(parseLine(sentence))
      .map(p => p.getCoveredText)
      .flatMap(phrase => wordAndWordPairExtractor.extractPhrases(phrase))
      .toSet

  //@tailrec
  private def traverseParseTree(parses: List[Parse]): List[Parse] = parses match {
    case (head: Parse) :: tail if potentiallyContainsConcept(head) =>
      head :: traverseParseTree(tail)
    case (head: Parse) :: tail =>
      traverseParseTree(head.getChildren.toList) ::: traverseParseTree(tail)
    case Nil => List.empty
  }

  private def parseLine(sentence: String) = {
    val TopnParses = 1
    ParserTool.parseLine(sentence, parser, TopnParses).toList
  }

  private def potentiallyContainsConcept(parse: Parse): Boolean =
    parse.getType == NounPhrase && !Seq("I", "me").contains(parse.getCoveredText)
}


