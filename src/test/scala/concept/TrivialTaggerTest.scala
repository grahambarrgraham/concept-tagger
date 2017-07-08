package concept

import concept.concepttaggers.TrivialConceptTagger
import concept.extractors.{TrivialNlpNounPhraseExtractor, TrivialPhraseExtractor}
import org.scalatest.{FlatSpec, Matchers}

class TrivialTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val trivialWorkflow: Workflow = Workflow(
    new TrivialPhraseExtractor,
    new TrivialConceptTagger,
    "tinyconceptlist.txt", Sequential)

  val trivialNlpWorkflowTinySetSequential: Workflow = Workflow(
    new TrivialNlpNounPhraseExtractor,
    new TrivialConceptTagger,
    "tinyconceptlist.txt", Sequential)

  "A trivial workflow" should
    behave like basicBehaviours(trivialWorkflow)

  "A workflow with trivial Nlp Noun Phrase Extraction and sequential matching" should
    behave like basicBehaviours(trivialNlpWorkflowTinySetSequential)

}
