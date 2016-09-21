create topic APP.FSNT.ADDRMKSTOPNR.RESPONSE.1 store=$sys.failsafe secure
grant topic APP.FSNT.ADDRMKSTOPNR.RESPONSE.1 user=svc_int_fsnt publish,subscribe

create topic APP.FSNT.SYSDELAYCHANGE.RECEIVE.1 store=$sys.failsafe secure
grant topic APP.FSNT.SYSDELAYCHANGE.RECEIVE.1 user=svc_int_fsnt publish,subscribe

create topic APP.FSNT.SYSSTATUSCHANGE.RECEIVE.1 store=$sys.failsafe secure
grant topic APP.FSNT.SYSSTATUSCHANGE.RECEIVE.1 user=svc_int_fsnt publish,subscribe




