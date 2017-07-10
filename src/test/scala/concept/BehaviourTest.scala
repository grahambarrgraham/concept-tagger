package concept

import concept.extractors.{NlpNounPhraseExtractor, WordAndWordPairExtractor}
import org.scalatest.{FlatSpec, Matchers}

class BehaviourTest extends FlatSpec with Matchers
  with Behaviours {

  val workflowWithSmallConceptSetSequentialMap: Workflow = Workflow(new WordAndWordPairExtractor, "tinyconceptlist.txt", Sequential)
  val workflowWithLargeConceptSetSequentialMap: Workflow = Workflow(new WordAndWordPairExtractor, "uuid-1M.txt", Sequential)
  val workflowWithNlpLargeConceptSetSequentialMap: Workflow = Workflow(new NlpNounPhraseExtractor, "uuid-1M.txt", Sequential)

  val workflowWithSmallConceptSetParallelMap: Workflow = Workflow(new WordAndWordPairExtractor, "tinyconceptlist.txt", Parallel)
  val workflowWithLargeConceptSetParallelMap: Workflow = Workflow(new WordAndWordPairExtractor, "uuid-1M.txt", Parallel)
  val workflowWithNlpLargeConceptSetParallelMap: Workflow = Workflow(new NlpNounPhraseExtractor, "uuid-1M.txt", Parallel)

  "A workflow using a simple word and word pair extractor on a small concept set and a sequential map" should
    behave like conceptTaggingBehaviour(workflowWithSmallConceptSetSequentialMap)

  "A workflow using a simple word and word pair extractor on a large concept set and a sequential map" should
    behave like conceptTaggingBehaviour(workflowWithLargeConceptSetSequentialMap)

  "A workflow using a NLP plus word and word pair extractor on a large concept set and a sequential map" should
    behave like conceptTaggingBehaviour(workflowWithNlpLargeConceptSetSequentialMap)

  "A workflow using a simple word and word pair extractor on a small concept set and a parallel map" should
    behave like conceptTaggingBehaviour(workflowWithSmallConceptSetParallelMap)

  "A workflow using a simple word and word pair extractor on a large concept set and a parallel map" should
    behave like conceptTaggingBehaviour(workflowWithLargeConceptSetParallelMap)

  "A workflow using a NLP plus word and word pair extractor on a large concept set and a parallel map" should
    behave like conceptTaggingBehaviour(workflowWithNlpLargeConceptSetParallelMap)

}
