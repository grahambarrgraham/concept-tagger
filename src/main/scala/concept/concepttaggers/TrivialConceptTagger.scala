package concept.concepttaggers

import concept.extractors.TrivialPhraseExtractor
import concept.{ConceptTagger, PhraseExtractor, TrivialMatchingConceptTagger}

import scala.collection.parallel.ParSeq

/**
  * Created by graham on 08/07/2017.
  */
class TrivialConceptTagger extends ConceptTagger {

  def findTags(phrase: String, tags: Seq[String]): Seq[String] = {
    tags.filter(p => matchTag(phrase, p))
  }

  def findTags(phrase: String, tags: ParSeq[String]): ParSeq[String] = {
    tags.filter(p => matchTag(phrase, p))
  }

  private def matchTag(phrase: String, p: String) = phrase.toLowerCase.contains(p.toLowerCase)
}
