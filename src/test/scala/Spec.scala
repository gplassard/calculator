package fr.gplassard.calculator

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.parsing.combinator.Parsers

trait Spec extends AnyFlatSpec with Matchers{
  def assertParse[T](result :Parsers#ParseResult[T], expected: T) = {
    if (result.successful) {
      result.get shouldBe expected
    } else {
      fail(result.toString)
    }
  }
}
