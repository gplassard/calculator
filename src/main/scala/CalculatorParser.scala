package fr.gplassard.calculator

import scala.util.parsing.combinator.RegexParsers

sealed trait Grammar
sealed trait Expression extends Grammar
case class Literal(v: Integer) extends Expression
case class Sum(left: Expression, right: Expression) extends Expression
case class Product(left: Expression, right: Expression) extends Expression
case class Division(left: Expression, right: Expression) extends Expression
case class VariableUsage(name: String) extends Expression
sealed trait Statement extends Grammar
case class VariableDefinition(name: String, ex: Expression) extends Statement

object CalculatorParser extends RegexParsers {
  def literal: Parser[Literal] = opt("-") ~ "\\d+".r ^^ { case neg ~ str => Literal((if (neg.isDefined) -1 else 1) * str.toInt) }
  def variableUsage: Parser[VariableUsage] = "\\w+".r ^^ { name => VariableUsage(name) }

  def sum: Parser[Sum] =  "(" ~>  expr ~ "+" ~ expr <~ ")" ^^ { case a ~ _ ~ b => Sum(a, b) }
  def product: Parser[Product] = "(" ~> expr ~ "*" ~ expr <~ ")" ^^ { case a ~ _ ~ b => Product(a, b) }
  def division: Parser[Division] = "(" ~> expr ~ "/" ~ expr <~ ")" ^^ { case a ~ _ ~ b => Division(a, b) }
  def expr: Parser[Expression] = sum | product | division | literal | variableUsage

  def variableDefinition: Parser[VariableDefinition] = "const " ~> "\\w+".r ~ "=" ~ expr ^^ {case name ~ _ ~ expr => VariableDefinition(name, expr)}
  def statement: Parser[Statement] = variableDefinition

  def grammar: Parser[Grammar] = statement | expr

  def apply(input: String): ParseResult[Grammar] = parseAll(grammar, input)
  def apply[T](input: String, parser: Parser[T]): ParseResult[T] = parseAll(parser, input)
}
