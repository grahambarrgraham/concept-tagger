package concept.extractors

import concept.PhraseExtractor

/**
  * Created by graham on 08/07/2017.
  */
class TrivialPhraseExtractor extends PhraseExtractor {
  def extractNounPhrases(sentence: String): Seq[String] = Seq(sentence)
}
