package concept

import concept.extractors.WordAndWordPairExtractor
import org.scalatest.{FlatSpec, Matchers}

class ParallelLargeMatchWholeSentenceTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(new WordAndWordPairExtractor, "wordlist.txt", Parallel)

  "A workflow with sequential basic compare on a tiny concept set" should behave like basicBehaviours(workflow)

}
