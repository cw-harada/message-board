package controllers

import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class HomeControllerSpec extends PlayFunSpec with GuiceOneAppPerTest {

  describe("HomeController") {
    it("should render the index page from a new instance of controller") {
      val controller = new HomeController
      val home       = controller.index().apply(FakeRequest())

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }
    it("should render the index page from the application") {
      val controller = app.injector.instanceOf[HomeController]
      val home       = controller.index().apply(FakeRequest())

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }
    it("should render the index page from the router") {
      val request = FakeRequest(GET, "/")
      val home    = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }
  }

}
