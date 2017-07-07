package concept

import scala.collection.parallel.ParSeq
import scala.collection.parallel.immutable.ParVector

object ConceptTagger {
  def tags: ParSeq[String] =
    ParVector(
      "Indian", "Thai", "Sushi",
      "Caribbean","Italian", "West Indian",
      "Pub", "East Asian", "BBQ",
      "Chinese", "Portuguese", "Spanish",
      "French", "East European"
    )
}

trait ConceptTagger {
  def findTags(s: String): Seq[String]
}

trait PhraseExtractor {
  def extractNounPhrases(sentence: String): Seq[String]
}
