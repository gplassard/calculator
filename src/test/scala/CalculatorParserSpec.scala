package fr.gplassard.calculator


import org.scalatest.flatspec.AnyFlatSpec

class CalculatorParserSpec extends Spec {

  "A Calculator Parser" should "parse a literal" in {
    assertParse(CalculatorParser("1"), Literal(1))
  }

  it should "parse a negative literal" in {
    assertParse(CalculatorParser("-21"), Literal(-21))
  }

  it should "parse a sum" in {
    assertParse(CalculatorParser("(1 + 15)"), Sum(Literal(1), Literal(15)))
  }

  it should "parse a sum with a negative left" in {
    assertParse(CalculatorParser("(-1 + 15)"), Sum(Literal(-1), Literal(15)))
  }

  it should "parse a sum with a negative right" in {
    assertParse(CalculatorParser("(1 + -15)"), Sum(Literal(1), Literal(-15)))
  }

  it should "parse a nested sum" in {
    assertParse(CalculatorParser("(1 + (2 + 3))"), Sum(Literal(1), Sum(Literal(2), Literal(3))))
  }

  it should "parse a product" in {
    assertParse(CalculatorParser("(1 * 15)"), Product(Literal(1), Literal(15)))
  }

  it should "parse a division" in {
    assertParse(CalculatorParser("(1 / 15)"), Division(Literal(1), Literal(15)))
  }

  it should "parse a variable usage" in {
    assertParse(CalculatorParser("constant"), VariableUsage("constant"))
  }

  it should "parse a statement" in {
    assertParse(CalculatorParser("const bla = 123"), VariableDefinition("bla", Literal(123)))
  }

  it should "parse a complex statement" in {
    assertParse(CalculatorParser("const bla = (1 * (3 + toto))"),  VariableDefinition("bla", Product(Literal(1), Sum(Literal(3), VariableUsage("toto")))))
  }

}
