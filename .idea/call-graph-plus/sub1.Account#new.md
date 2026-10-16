```plantuml
@startuml
participant Actor
create Account
Actor -> Account : new
activate Account
return
@enduml
