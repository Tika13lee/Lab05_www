# Lab week 05: Ứng dụng web tuyển dụng việc làm sử dụng spring boot

## Thông tin sinh viên:

- **Họ và tên:** Lê Thị Thúy Kiều
- **Mã sinh viên:** 21108651
- **Lớp:** DHKTPM17C
- **Môn học:** Lập trình www với công nghê java - IUH - DHKTPM17A
- **Giảng viên:** TS Võ Văn Hải

[Link Github](https://github.com/Tika13lee/Lab05_www)

[Link LeThiThuyKieu_21108651_Report](https://docs.google.com/document/d/1f7qCbTjiAhO3QwVGwuSJX3YV2_OhLdyzQzibuuI0A9U/edit?tab=t.0#heading=h.tzuotsfng98s)

## Kết quả đạt được

Mở trình duyệt và truy cập vào địa chỉ http://localhost:8080

- **Trang Login:** chọn role và nhập mã sau đó nhấn nút `Login`
  ![login](https://i.ibb.co/8PZ78rM/login.png)

### 1. Đăng nhập với vai trò company

- **Trang chủ:** hiển thị các bài đăng công việc và thông tin của công ty
  ![company-home](https://i.ibb.co/VDLbCtH/company-home.png)
    - Chọn `Post Job` để thêm công việc mới
    - Chọn biểu tượng ở cột `Edit` để chỉnh sửa công việc
    - Chọn biểu tượng ở cột `Delete` để xóa công việc
    - Chọn biểu tượng ở cột `View` để xem các ứng viên ứng phù hợp với công việc theo kỹ năng
    - Chọn `Logout` để đăng xuất
- **Trang thêm công việc:**
  ![add-job](https://i.ibb.co/L1QmMS5/add-job.png)
    - Nhập thông tin công việc
    - Chọn kỹ năng cần thiết
        - Nhấn `Add Skill` để thêm kỹ năng
        - Nhấn biểu tượng `x` để xóa kỹ năng
    - Chọn `Save Job` để thêm công việc
    - Chọn `Home` để quay lại trang chủ
- **Trang xem các ứng viên phù hợp với công việc theo kỹ năng:** hiển thị thông tin công việc và ứng viên phù hợp với
  công việc
  *(các ứng viên đã được sắp xếp theo mức độ nhiều kỹ năng phù hợp với công việc nhất)*
  ![view-candidate](https://i.ibb.co/2nG355Y/view-candidates.png)
    - Chọn biểu tượng ở cột `Detail` để xem thông tin ứng viên
    - Chọn `Sent mail` để gửi mail cho ứng viên
    - Chọn `Home` để quay lại trang chủ

### 2. Đăng nhập với vai trò candidate