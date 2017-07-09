package concept

import scala.collection.parallel.{ParMap, ParSet}

trait PhraseExtractor {
  def extractPhrases(sentence: String): Set[String]
}

sealed trait Mode
case object Parallel extends Mode
case object Sequential extends Mode

case class Workflow(extractor: PhraseExtractor, conceptFile: String, mode: Mode) {

  val conceptMap: Map[String, String] = readConcepts.map(l => l.toLowerCase -> l).toMap
  val parallelConceptMap: ParMap[String, String] = conceptMap.par

  def getConceptTags(sentence: String): Set[String] = mode match {
    case Parallel => getConceptTagsParallel(sentence)
    case Sequential => getConceptTagsSequential(sentence)
  }

  def getConceptTagsSequential(sentence: String): Set[String] =
    extractor.extractPhrases(sentence).map(phrase => conceptMap.get(phrase.toLowerCase)).flatten

  def getConceptTagsParallel(sentence: String): Set[String] =
    extractor.extractPhrases(sentence).map(phrase => parallelConceptMap.get(phrase.toLowerCase)).flatten

  private def readConcepts = {
    ConceptFileReader.readLines(conceptFile).get
  }

  override def toString: String = s"(${mode}-${conceptFile}-${extractor.getClass.getSimpleName}"

}


