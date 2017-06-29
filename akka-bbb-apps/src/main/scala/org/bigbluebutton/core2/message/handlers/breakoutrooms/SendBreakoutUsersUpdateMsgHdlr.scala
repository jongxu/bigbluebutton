package org.bigbluebutton.core2.message.handlers.breakoutrooms

import org.bigbluebutton.common2.msgs.{ BreakoutUserVO, SendBreakoutUsersUpdateMsg }
import org.bigbluebutton.core.OutMessageGateway
import org.bigbluebutton.core.api.BreakoutRoomUsersUpdate
import org.bigbluebutton.core.bus.BigBlueButtonEvent
import org.bigbluebutton.core.models.Users1x
import org.bigbluebutton.core.running.MeetingActor

trait SendBreakoutUsersUpdateMsgHdlr {
  this: MeetingActor =>

  val outGW: OutMessageGateway

  def handleSendBreakoutUsersUpdateMsg(msg: SendBreakoutUsersUpdateMsg): Unit = {

    val users = Users1x.getUsers(liveMeeting.users)
    val breakoutUsers = users map { u => new BreakoutUserVO(u.externalId, u.name) }
    eventBus.publish(BigBlueButtonEvent(props.breakoutProps.parentId,
      new BreakoutRoomUsersUpdate(props.breakoutProps.parentId, props.meetingProp.intId, breakoutUsers)))
  }
}
