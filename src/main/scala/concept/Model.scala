package concept

import scala.collection.parallel.ParSeq
import scala.collection.parallel.immutable.ParVector

trait ConceptTagger {
  def findTagsSequential(phrase: String, tags: Seq[String]): Seq[String]
  def findTagsParallel(phrase: String, tags: ParSeq[String]): ParSeq[String]
}

trait PhraseExtractor {
  def extractNounPhrases(sentence: String): Seq[String]
}

sealed trait Mode
final case object Parallel extends Mode
final case object Sequential extends Mode

case class Workflow(extractor: PhraseExtractor, tagger: ConceptTagger, conceptFile: String, mode: Mode) {

  val conceptList: Seq[String] = ConceptFileReader.readLines(conceptFile).get
  val parallelConceptList: ParSeq[String] = ConceptFileReader.readLines(conceptFile).get.par

  def getConceptTags(sentence: String, mode: Mode): Set[String] = mode match {
    case Parallel => getConceptTagsParallel(sentence)
    case Sequential => getConceptTagsSequential(sentence)
  }

  def getConceptTagsSequential(sentence: String): Set[String] =
    extractor.extractNounPhrases(sentence)
      .flatMap(phrase => tagger.findTagsSequential(phrase, conceptList)).toSet

  def getConceptTagsParallel(sentence: String): Set[String] =
    extractor.extractNounPhrases(sentence)
      .flatMap(phrase => tagger.findTagsParallel(phrase, parallelConceptList)).toSet
}


