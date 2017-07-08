package concept

import concept.concepttaggers.TrivialConceptTagger
import concept.extractors.{TrivialNlpNounPhraseExtractor, TrivialPhraseExtractor}
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

trait TaggerBehaviours { this: FlatSpec with Matchers =>

  def basicBehaviours(workflow: Workflow) {

    "I would like some thai food" should "match Thai concept" in {
      workflow.getConceptTags("I would like some thai food") should contain only "Thai"
    }

    "Where can I find good sushi" should "match Sushi concept" in {
      workflow.getConceptTags("Where can I find good sushi") should contain only "Sushi"
    }

    "Find me a place that does tapas" should "match nothing" in {
      workflow.getConceptTags("Find me a place that does tapas") shouldBe empty
    }

    "Which restaurants do East Asian food" should "match East Asian" in {
      workflow.getConceptTags("Which restaurants do East Asian food") should contain only "East Asian"
    }

    "Which restaurants do West Indian food" should "match Indian and West Indian" in {
      workflow.getConceptTags("Which restaurants do West Indian food") should contain only("West Indian", "Indian")
    }

    "What is the weather like today" should "match nothing" in {
      workflow.getConceptTags("What is the weather like today") shouldBe empty
    }

    "Which restaurants do West or East Indian food or Sushi" should "match Indian and East Indian and Sushi" in {
      workflow.getConceptTags("Which restaurants do East or West Indian food or Spanish Sushi") should contain only("West Indian", "Indian", "Sushi", "Spanish")
    }
  }
}
