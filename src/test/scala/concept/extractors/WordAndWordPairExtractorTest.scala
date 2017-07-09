package concept.extractors

import org.scalatest.{FlatSpec, FunSuite, Matchers}

/**
  * Created by graham on 08/07/2017.
  */
class WordAndWordPairExtractorTest extends FlatSpec with Matchers {

  val extractor = new WordAndWordPairExtractor()

  "empty string" should "return empty list" in {
    extractor.extractPhrases("") shouldBe empty
  }

  "one string" should "return string" in {
    extractor.extractPhrases("hello") should contain only "hello"
  }

  "two strings" should "return 3 options" in {
    extractor.extractPhrases("hello world") should contain only ("hello", "hello world", "world")
  }

  "three strings" should "return 5 options" in {
    extractor.extractPhrases("hello world again") should contain only ("hello", "hello world", "world", "world again", "again")
  }

  "four strings" should "return 7 options" in {
    extractor.extractPhrases("hello world again dear") should contain only ("hello", "hello world", "world", "world again", "again", "again dear", "dear")
  }

  "two strings with various whitespace" should "return 3 options" in {
    extractor.extractPhrases("  hello    world  ") should contain only ("hello", "hello world", "world")
  }

}
