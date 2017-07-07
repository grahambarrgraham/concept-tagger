package concept

import scala.collection.parallel.ParSeq
import scala.collection.parallel.immutable.ParVector

class NlpExtractorWithTrivialMatchingTagger extends ConceptTagger {

  val extractor: PhraseExtractor = new OpenNlpNounPhraseExtractor
  val phraseTagger: ConceptTagger = new TrivialMatchingConceptTagger

  def findTags(sentence: String): Seq[String] =
    extractor.extractNounPhrases(sentence).flatMap(phrase => phraseTagger.findTags(phrase))
}

class TrivialTagger extends ConceptTagger {

  val extractor: PhraseExtractor = new TrivialPhraseExtractor
  val phraseTagger: ConceptTagger = new TrivialMatchingConceptTagger

  def findTags(sentence: String): Seq[String] =
    extractor.extractNounPhrases(sentence).flatMap(phrase => phraseTagger.findTags(phrase))
}

class TrivialMatchingConceptTagger extends ConceptTagger {
  def findTags(phrase: String): Seq[String] = {
    ConceptTagger.tags.filter(p => phrase.toLowerCase.contains(p.toLowerCase)).seq
  }
}
