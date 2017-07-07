package concept

import org.scalatest.{FlatSpec, Matchers}

class NlpExtractorWithTrivialMatchingTaggerTest extends FlatSpec with Matchers {

  val tagger: ConceptTagger = new NlpExtractorWithTrivialMatchingTagger

  "I would like some thai food" should "match Thai concept" in {
    tagger.findTags("I would like some thai food") should contain only "Thai"
  }

  "Where can I find good sushi" should "match Sushi concept" in {
    tagger.findTags("Where can I find good sushi") should contain only "Sushi"
  }

  "Find me a place that does tapas" should "match nothing" in {
    tagger.findTags("Find me a place that does tapas") shouldBe empty
  }

  "Which restaurants do East Asian food" should "match East Asian" in {
    tagger.findTags("Which restaurants do East Asian food") should contain only "East Asian"
  }

  "Which restaurants do West Indian food" should "match Indian and West Indian" in {
    tagger.findTags("Which restaurants do West Indian food") should contain only ("West Indian", "Indian")
  }

  "What is the weather like today" should "match nothing" in {
    tagger.findTags("What is the weather like today") shouldBe empty
  }

  "Which restaurants do West or East Indian food or Sushi" should "match Indian and East Indian and Sushi" in {
    tagger.findTags("Which restaurants do East or West Indian food or Spanish Sushi") should contain only ("West Indian", "Indian", "Sushi", "Spanish")
  }

}
