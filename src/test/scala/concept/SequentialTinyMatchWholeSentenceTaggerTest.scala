package concept

import concept.concepttaggers.TrivialConceptTagger
import concept.extractors.{TrivialNlpNounPhraseExtractor, TrivialPhraseExtractor}
import org.scalatest.{FlatSpec, Matchers}

class SequentialTinyMatchWholeSentenceTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(
    new TrivialPhraseExtractor,
    new TrivialConceptTagger,
    "tinyconceptlist.txt", Sequential)

  "A workflow with sequential basic compare on a tiny concept set" should behave like basicBehaviours(workflow)

}
