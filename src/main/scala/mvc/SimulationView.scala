package mvc

import system.items.ItemType._
import system.level.LevelMap
import system.robot.Robot
import system.robot.RobotType.RobotType

/** View in Model-View-Controller pattern. */
trait SimulationView {
  private var controller: Option[SimulationController] = None

  def registerController(c: SimulationController) = {
    controller = Some(c)
  }

  /** Tell the view to update using given map and robots. */
  def redraw(levelMap: LevelMap, robots: Array[Robot]): Unit

  /** Tell the view to update statistics for specific type of robot. */
  def updateStatistics(rt: RobotType, averageTime: Long, itemCount: Int): Unit

  protected def onStartSimulationButtonClicked(producerProbabilities: Map[ItemType, Double],
                                                   consumerProbabilities: Map[ItemType, Double],
                                                   robotCounts: Map[RobotType, Int]): Unit = {
    if(controller.isDefined)
      controller.get.onStartSimulationButtonClicked(
        producerProbabilities, consumerProbabilities, robotCounts)
  }

  protected def onStopSimulationButtonClicked(): Unit = {
    if(controller.isDefined)
      controller.get.onStopSimulationButtonClicked()
  }

  protected def onNewSpeedSet(speed: Double): Unit = {
    if(controller.isDefined)
      controller.get.onNewSpeedSet(speed)
  }

  protected def onResetStatisticsButtonClicked(): Unit = {
    if(controller.isDefined)
      controller.get.onResetStatisticsButtonClicked()
  }
}