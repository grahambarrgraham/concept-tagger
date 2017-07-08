package concept

import concept.extractors.TrivialNlpNounPhraseExtractor
import org.scalatest.{FlatSpec, Matchers}

class SequentialTinyMatchNlpNounPhrasesTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(
    new TrivialNlpNounPhraseExtractor,
    "tinyconceptlist.txt", Sequential)

  "A workflow with sequential compare of NLP detected noun phrases on a tiny concept set" should behave like basicBehaviours(workflow)

}
