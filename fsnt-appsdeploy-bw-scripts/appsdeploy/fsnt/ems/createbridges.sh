create bridge source=topic:ENT.FLT.FES.FLTLEG.2 target=queue:APP.FSNT.OEP.RECEIVE.1 selector="EventSubType in ('DepGate','EstArrTime','UndelayArrTime','EstDepTime','FlightCancelled','FlightUnlock','OffTime','OutTime','StubNewOrigin','UndelayDepTime')"
create bridge source=topic:ENT.FLT.FES.FLTLEG.SECURE.2 target=queue:APP.FSNT.OEP.RECEIVE.1 selector="EventSubType in ('FlightLock')"
create bridge source=topic:APP.NM.DLVRY.REQ.TOPIC.1 target=queue:APP.FSNT.NMSTATUSTRACKER.RECEIVE.1 selector="RegistrationID like ('FSNT_%')"
