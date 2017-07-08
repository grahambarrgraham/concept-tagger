package concept

import org.scalatest.prop.TableDrivenPropertyChecks._
import concept.concepttaggers.TrivialConceptTagger
import concept.extractors.{TrivialNlpNounPhraseExtractor, TrivialPhraseExtractor}
import org.scalatest.{FlatSpec, Matchers}

class SequentialTinyMatchNlpNounPhrasesTaggerTest extends FlatSpec with Matchers with TaggerBehaviours {

  val workflow: Workflow = Workflow(
    new TrivialNlpNounPhraseExtractor,
    new TrivialConceptTagger,
    "tinyconceptlist.txt", Sequential)

  "A workflow with sequential compare of NLP detected noun phrases on a tiny concept set" should behave like basicBehaviours(workflow)

}
