package concept

import concept.extractors.{NlpNounPhraseExtractor, WordAndWordPairExtractor}
import org.scalatest.{FlatSpec, Matchers}

class BehaviourTest extends FlatSpec with Matchers
  with Behaviours {

  val workflowWithSmallConceptSetSequentialMap: Workflow = Workflow(new WordAndWordPairExtractor, "tinyconceptlist.txt", Sequential)
  val workflowWithLargeConceptSetSequentialMap: Workflow = Workflow(new WordAndWordPairExtractor, "70kwordlist.txt", Sequential)
  val workflowWithNlpLargeConceptSetSequentialMap: Workflow = Workflow(new NlpNounPhraseExtractor, "70kwordlist.txt", Sequential)

  val workflowWithSmallConceptSetParallelMap: Workflow = Workflow(new WordAndWordPairExtractor, "tinyconceptlist.txt", Parallel)
  val workflowWithLargeConceptSetParallelMap: Workflow = Workflow(new WordAndWordPairExtractor, "70kwordlist.txt", Parallel)
  val workflowWithNlpLargeConceptSetParallelMap: Workflow = Workflow(new NlpNounPhraseExtractor, "70kwordlist.txt", Parallel)

  "A workflow using a simple word and word pair extractor on a small concept set and a sequential map" should
    behave like smallConceptSetBahaviours(workflowWithSmallConceptSetSequentialMap)

  "A workflow using a simple word and word pair extractor on a large concept set and a sequential map" should
    behave like largeConceptSetBehaviours(workflowWithLargeConceptSetSequentialMap)

  "A workflow using a NLP plus word and word pair extractor on a large concept set and a sequential map" should
    behave like largeConceptSetBehaviours(workflowWithNlpLargeConceptSetSequentialMap)

  "A workflow using a simple word and word pair extractor on a small concept set and a parallel map" should
    behave like smallConceptSetBahaviours(workflowWithSmallConceptSetParallelMap)

  "A workflow using a simple word and word pair extractor on a large concept set and a parallel map" should
    behave like largeConceptSetBehaviours(workflowWithLargeConceptSetParallelMap)

  "A workflow using a NLP plus word and word pair extractor on a large concept set and a parallel map" should
    behave like largeConceptSetBehaviours(workflowWithNlpLargeConceptSetParallelMap)

}
