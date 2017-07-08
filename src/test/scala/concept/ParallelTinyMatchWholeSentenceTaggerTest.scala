package concept

import concept.concepttaggers.TrivialConceptTagger
import concept.extractors.TrivialPhraseExtractor
import org.scalatest.{FlatSpec, Matchers}

class ParallelTinyMatchWholeSentenceTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(
    new TrivialPhraseExtractor,
    new TrivialConceptTagger,
    "tinyconceptlist.txt", Parallel)

  "A workflow with sequential basic compare on a tiny concept set" should behave like basicBehaviours(workflow)

}
