package test

import org.specs2._
import specification.script.{GWT, StandardDelimitedStepParsers, StepParser}

class GWTSpec extends Specification with GWT with StandardDelimitedStepParsers { def is =
  
   s2"""

    Test specs2 parsing with different delimiter.
      Given a valid JSON string #{"username":"bob", "password":"secure"}# ${signup.start}
      When compare parsed string with delimiter {#}
      Then I expect the string between the delimiter { notUsed }                                     ${signup.end}
    """

  val aJsonString = StepParser((s: String) => s)("""#([^#]+)#""".r)
  
  val expectedJSON = "{\"username\":\"bob\", \"password\":\"secure\"}"
  
  lazy val signup = Scenario("signUp").
    given(aJsonString).
    when(aString) { case delimiter :: parsedGivenLine :: _ =>  passParsedJSONString(parsedGivenLine, delimiter) }.
    andThen(aString) { case parsedThenString :: parsedGivenString :: _ => expectedJSON == parsedGivenString }

  def passParsedJSONString(jsonString: String, apiCallString: String): String = {
    return jsonString
  }

}