package concept

object ConceptTagger {
  def tags: Seq[String] =
    Seq(
    "Indian", "Thai", "Sushi",
    "Caribbean","Italian", "West Indian",
    "Pub", "East Asian", "BBQ",
    "Chinese", "Portuguese", "Spanish",
    "French", "East European"
  )
}

trait ConceptTagger {
  def findTags(s: String): Seq[String]
}

class SentenceConceptTagger extends ConceptTagger {

  val extractor: NounPhraseExtractor = new OpenNlpNounPhraseExtractor
  val phraseTagger: ConceptTagger = new TrivialMatchingConceptTagger

  def findTags(sentence: String): Seq[String] =
    extractor.extract(sentence).flatMap(phrase => phraseTagger.findTags(phrase))
}

class TrivialMatchingConceptTagger extends ConceptTagger {
  def findTags(sentence: String): Seq[String] = ConceptTagger.tags.filter(p => sentence.toLowerCase.contains(p.toLowerCase))
}
