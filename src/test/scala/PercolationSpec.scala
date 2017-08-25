import org.scalatest.{FlatSpec, GivenWhenThen, Matchers}

class PercolationSpec extends FlatSpec
  with Matchers
  with GivenWhenThen {

  "Percolation" should "percolate on expected straight line from top to bottom" in {

    Given("Initialized Percolation object")
    val side: Int = 4
    val percolation = new Percolation(side)

    When("Top to bottom path is created")
    percolation.open(1, 1)
    percolation.open(2, 1)
    percolation.open(3, 1)
    percolation.open(4, 1)

    Then("object is percolated")

    withClue("system percolates") {
      percolation.percolates() shouldBe true
    }
  }

  it should "not percolate if path is broken" in {

    Given("Initialized Percolation object")
    val side: Int = 4
    val percolation = new Percolation(side)

    When("Some path is created which is know not to be able to percolate")
    percolation.open(1, 1)
    percolation.open(2, 2)
    percolation.open(3, 3)
    percolation.open(4, 4)

    withClue("system not percolates") {
      percolation.percolates() shouldBe false
    }
  }

  it should "have correct full sites if system percolates" in {

    Given("Initialized Percolation object")
    val side: Int = 4
    val percolation = new Percolation(side)

    When("Top to bottom path is created")
    percolation.open(1, 1)
    percolation.open(2, 1)
    percolation.open(3, 1)
    percolation.open(4, 1)

    withClue("path is full") {
      percolation.isFull(1, 1) shouldBe true
      percolation.isFull(2, 1) shouldBe true
      percolation.isFull(3, 1) shouldBe true
      percolation.isFull(4, 1) shouldBe true
    }

  }

}
