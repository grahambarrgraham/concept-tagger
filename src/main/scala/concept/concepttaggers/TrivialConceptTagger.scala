package concept.concepttaggers

import concept.ConceptTagger

import scala.collection.parallel.ParSeq

/**
  * Created by graham on 08/07/2017.
  */
class TrivialConceptTagger extends ConceptTagger {

  def findTagsSequential(phrase: String, tags: Seq[String]): Seq[String] = {
    tags.filter(p => matchTag(phrase, p))
  }

  def findTagsParallel(phrase: String, tags: ParSeq[String]): ParSeq[String] = {
    tags.filter(p => matchTag(phrase, p))
  }

  private def matchTag(phrase: String, p: String) = phrase.toLowerCase.contains(p.toLowerCase)
}
