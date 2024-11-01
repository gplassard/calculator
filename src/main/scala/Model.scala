package fr.gplassard.calculator

sealed trait Grammar
sealed trait Expression extends Grammar
case class Literal(v: Integer) extends Expression
case class Sum(left: Expression, right: Expression) extends Expression
case class Product(left: Expression, right: Expression) extends Expression
case class Division(left: Expression, right: Expression) extends Expression
case class VariableUsage(name: String) extends Expression
sealed trait Statement extends Grammar
case class VariableDefinition(name: String, ex: Expression) extends Statement