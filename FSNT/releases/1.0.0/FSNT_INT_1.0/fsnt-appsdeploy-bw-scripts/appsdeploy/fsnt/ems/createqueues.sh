create queue APP.FSNT.ADDRMKSTOPNR.REQUEST.1 store=$sys.failsafe secure
grant queue APP.FSNT.ADDRMKSTOPNR.REQUEST.1 user=svc_INT_FSNT send,receive

create queue APP.FSNT.NMHANDLER.RECEIVE.1 store=$sys.failsafe secure
grant queue APP.FSNT.NMHANDLER.RECEIVE.1 user=svc_INT_FSNT send,receive

create queue APP.FSNT.NMSTATUSTRACKER.RECEIVE.1 store=$sys.failsafe secure
grant queue APP.FSNT.NMSTATUSTRACKER.RECEIVE.1 user=svc_INT_FSNT send,receive

create queue APP.FSNT.OEP.RECEIVE.1 store=$sys.failsafe secure
grant queue APP.FSNT.OEP.RECEIVE.1 user=svc_INT_FSNT send,receive

create queue APP.FSNT.PNRLOADER.RECEIVE.1 store=$sys.failsafe secure
grant queue APP.FSNT.PNRLOADER.RECEIVE.1 user=svc_INT_FSNT send,receive

create queue APP.FSNT.SVC.DASHBOARD.DASHBOARD.REQ store=$sys.failsafe secure
grant queue APP.FSNT.SVC.DASHBOARD.DASHBOARD.REQ user=svc_INT_FSNT send,receive

create queue APP.FSNT.SVC.DASHBOARD.SYSTEMDATA.REQ store=$sys.failsafe secure
grant queue APP.FSNT.SVC.DASHBOARD.SYSTEMDATA.REQ user=svc_INT_FSNT send,receive

create queue APP.FSNT.SVC.DASHBOARD.SYSTEMDELAY.REQ store=$sys.failsafe secure
grant queue APP.FSNT.SVC.DASHBOARD.SYSTEMDELAY.REQ user=svc_INT_FSNT send,receive

create queue APP.FSNT.SVC.DASHBOARD.SYSTEMSTATUS.REQ store=$sys.failsafe secure
grant queue APP.FSNT.SVC.DASHBOARD.SYSTEMSTATUS.REQ user=svc_INT_FSNT send,receive
