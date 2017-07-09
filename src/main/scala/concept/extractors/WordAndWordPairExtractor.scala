package concept.extractors

import concept.PhraseExtractor

import scala.annotation.tailrec

/**
  * Created by graham on 08/07/2017.
  */
class WordAndWordPairExtractor extends PhraseExtractor {

  def extractPhrases(sentence: String): Set[String] = {
    val singleWords = sentence.trim.split(" +").filterNot(s => s == "").toList
    (twoWordPhrases(singleWords) ::: singleWords).toSet
  }

  private def twoWordPhrases(split: List[String]) = {
    pairs(split, Nil).map(t => s"${t._1} ${t._2}")
  }

  @tailrec
  private def pairs(words: List[String], acc: List[(String, String)]): List[(String, String)] = words match {
    case h :: n :: tail => pairs(n :: tail, (h, n) :: acc)
    case h :: Nil => acc
    case Nil => acc
  }


}
