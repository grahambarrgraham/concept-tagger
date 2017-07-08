package concept

import concept.extractors.WordAndWordPairExtractor
import org.scalatest.{FlatSpec, Matchers}

class SequentialTinyMatchWholeSentenceTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(
    new WordAndWordPairExtractor,
    "tinyconceptlist.txt", Sequential)

  "A workflow with sequential basic compare on a tiny concept set" should behave like basicBehaviours(workflow)

}
