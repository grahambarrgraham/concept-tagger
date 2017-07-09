package concept

import org.scalatest.{FlatSpec, Matchers}

trait Behaviours {
  this: FlatSpec with Matchers =>

  def smallConceptSetBahaviours(workflow: Workflow) {

    s"$workflow Which restaurants do East Asian food" should "match East Asian" in {
      workflow.getConceptTags("Which restaurants do East Asian food") should contain only "East Asian"
    }

    s"$workflow I would like some thai food" should "match Thai concept" in {
      workflow.getConceptTags("I would like some thai food") should contain only "Thai"
    }

    s"$workflow Where can I find good sushi" should "match Sushi concept" in {
      workflow.getConceptTags("Where can I find good sushi") should contain only "Sushi"
    }

    s"$workflow Find me a place that does tapas" should "match nothing" in {
      workflow.getConceptTags("Find me a place that does tapas") shouldBe empty
    }

    s"$workflow Which restaurants do West Indian food" should "match Indian and West Indian" in {
      workflow.getConceptTags("Which restaurants do West Indian food") should contain only("West Indian", "Indian")
    }

    s"$workflow What is the weather like today" should "match nothing" in {
      workflow.getConceptTags("What is the weather like today") shouldBe empty
    }

    s"$workflow Which restaurants do West or East Indian food or Sushi" should "match Indian and East Indian and Sushi" in {
      workflow.getConceptTags("Which restaurants do East or West Indian food or Spanish Sushi") should contain only("West Indian", "Indian", "Sushi", "Spanish")
    }
  }

  def largeConceptSetBehaviours(workflow: Workflow) {

    s"$workflow Which restaurants do East Asian food" should "match East Asian" in {
      workflow.getConceptTags("Which restaurants do East Asian food") should contain("East Asian")
    }

    s"$workflow I would like some thai food" should "match Thai concept" in {
      workflow.getConceptTags("I would like some thai food") should contain("Thai")
    }

    s"$workflow Where can I find good sushi" should "match Sushi concept" in {
      workflow.getConceptTags("Where can I find good sushi") should contain("Sushi")
    }

    s"$workflow Non dictionary word" should "match nothing" in {
      workflow.getConceptTags("MZZZ anyd wiiiz cannndot beee sessses") shouldBe empty
    }

    s"$workflow Which restaurants do West Indian food" should "match Indian and West Indian" in {
      workflow.getConceptTags("Which restaurants do West Indian food") should contain allOf("West Indian", "Indian")
    }

    s"$workflow Which restaurants do West or East Indian food or Sushi" should "match Indian and East Indian and Sushi" in {
      workflow.getConceptTags("Which restaurants do East or West Indian food or Spanish Sushi") should contain allOf("West Indian", "Indian", "Sushi", "Spanish")
    }
  }
}
