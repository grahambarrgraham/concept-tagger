package concept

import concept.extractors.WordAndWordPairExtractor
import org.scalatest.{FlatSpec, Matchers}

class ParallelTinyMatchWholeSentenceTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(
    new WordAndWordPairExtractor,
    "tinyconceptlist.txt", Parallel)

  "A workflow with sequential basic compare on a tiny concept set" should behave like basicBehaviours(workflow)

}
