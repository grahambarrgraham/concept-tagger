package concept

import scala.collection.parallel.ParSet

trait PhraseExtractor {
  def extractPhrases(sentence: String): Set[String]
}

sealed trait Mode
case object Parallel extends Mode
case object Sequential extends Mode

case class Workflow(extractor: PhraseExtractor, conceptFile: String, mode: Mode) {

  val conceptSet: Set[String] = ConceptFileReader.readLines(conceptFile).get.map(l => l.toLowerCase).toSet
  val parallelConceptSet: ParSet[String] = conceptSet.par

  def getConceptTags(sentence: String): Set[String] = mode match {
    case Parallel => getConceptTagsParallel(sentence)
    case Sequential => getConceptTagsSequential(sentence)
  }

  def getConceptTagsSequential(sentence: String): Set[String] =
    extractor.extractPhrases(sentence).intersect(conceptSet)

  def getConceptTagsParallel(sentence: String): Set[String] =
    extractor.extractPhrases(sentence).intersect(parallelConceptSet).seq
}


