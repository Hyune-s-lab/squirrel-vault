# squirrel-vault

## background

![image](https://github.com/user-attachments/assets/1c36c35a-a98e-45d2-9cb6-cdcb63a914c2)

- 각 서비스에서 처리되는 작업의 일부는 과금 대상으로 수집되어야 합니다.
- 과금이 계산되는 정책은 다양할 수 있습니다. 예시로는
    - 종류: request count, token count, period
    - 중계되는 서비스 (source), 실 요청자 (requester)
    - 선불제, 종량제
- 과금 정보가 들어오는 형태는 다양할 수 있습니다.
    - message queue, single/bulk api request, file
- 각 요청의 스키마는 다양할 수 있으며, 일부 유실의 가능성이 있습니다.

> 과금 대상인지 알 수 없는 정보가 (task, action_event) 과금 정보로 변환되고 (billing_data)  
> 과금 정책에 (billing_policy) 에 따라 리포트가 (summary, billing_report) 생성되어야 합니다.  
> data, info, event 가 많이 사용되어야 하는 로직은 db table, service, dto 네이밍이 어렵고, 로직 이해가 어려워집니다.
>
> 이를 위해 본 프로젝트의 코드에서는 도메인 용어로서 과금 정보를 acorn (도토리) 라는 대명사로 표현 합니다.

### as-is

![image](https://github.com/user-attachments/assets/be194df0-5f91-429a-a6c4-7796968370f9)

- 각 서비스에서 처리된 작업은 db 에 저장 됩니다.
- db 에 저장된 작업 정보의 일부는 과금 대상 입니다.
- 개발자는 요청에 따라 각 db 에서 과금 대상 작업을 선별하고 리포트 형태로 매핑 합니다.

## phase

### phase 1

![image](https://github.com/user-attachments/assets/eb604a20-3d32-4357-aba8-c2763f33cf16)

- 과금 정보는 collector 서비스의 api 로 수집 됩니다.
- 각 서비스는 서로 다른 버전의 스키마로 개발되어 있습니다.
- 데모 실행 방법
    - `feature/phase1` 브랜치 `/acorn-collector-api/http-client/demo.http` 
