# EmployeeControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addEmployee**](EmployeeControllerApi.md#addEmployee) | **POST** /employee | Add Employee |
| [**addEmployeeToDepartment**](EmployeeControllerApi.md#addEmployeeToDepartment) | **POST** /department/employee/add | Add Employee to department |
| [**allEmployees**](EmployeeControllerApi.md#allEmployees) | **GET** /employees | List all Employees |
| [**deleteEmployee**](EmployeeControllerApi.md#deleteEmployee) | **DELETE** /employee/{empId} | Delete Employee |
| [**employeesAndDepartment**](EmployeeControllerApi.md#employeesAndDepartment) | **GET** /department/employees | Department employees |
| [**employeesInBatch**](EmployeeControllerApi.md#employeesInBatch) | **GET** /employees/batch | Employees in Batch |
| [**employeesInDepartment**](EmployeeControllerApi.md#employeesInDepartment) | **GET** /employees/department | Department employees count |
| [**employeesJoinedIn**](EmployeeControllerApi.md#employeesJoinedIn) | **GET** /employees/joined | Employee joined recently |
| [**employeesWithSalaryGreaterThan**](EmployeeControllerApi.md#employeesWithSalaryGreaterThan) | **GET** /employees/salary | Find Employees with salary greater than |
| [**incrementEmployeesSalary**](EmployeeControllerApi.md#incrementEmployeesSalary) | **PUT** /employees/salary/hike | Update Employees salary |
| [**removeEmployeeFromDepartment**](EmployeeControllerApi.md#removeEmployeeFromDepartment) | **PUT** /department/employee/remove | Remove Employee from department |
| [**topThreeHighestPaidEmployees**](EmployeeControllerApi.md#topThreeHighestPaidEmployees) | **GET** /employees/top/salaries | Top Three salaried employees |
| [**transferEmployeeDepartment**](EmployeeControllerApi.md#transferEmployeeDepartment) | **PUT** /department/transfer | Transfer Employees Department |
| [**updateEmployee**](EmployeeControllerApi.md#updateEmployee) | **PUT** /employee/{empId} | Update Employee |


<a id="addEmployee"></a>
# **addEmployee**
> Employee addEmployee(deptId, employeeDto)

Add Employee

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer deptId = 56; // Integer | Id of the department
    EmployeeDto employeeDto = new EmployeeDto(); // EmployeeDto | Employee to create
    try {
      Employee result = apiInstance.addEmployee(deptId, employeeDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#addEmployee");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **deptId** | **Integer**| Id of the department | |
| **employeeDto** | [**EmployeeDto**](EmployeeDto.md)| Employee to create | |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | New Employee created |  -  |

<a id="addEmployeeToDepartment"></a>
# **addEmployeeToDepartment**
> Employee addEmployeeToDepartment(empId, deptId)

Add Employee to department

Employee Id &amp; Department Id should be valid 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer empId = 56; // Integer | Id of the employee
    Integer deptId = 56; // Integer | Id of the department
    try {
      Employee result = apiInstance.addEmployeeToDepartment(empId, deptId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#addEmployeeToDepartment");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **empId** | **Integer**| Id of the employee | |
| **deptId** | **Integer**| Id of the department | |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="allEmployees"></a>
# **allEmployees**
> List&lt;Employee&gt; allEmployees()

List all Employees

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    try {
      List<Employee> result = apiInstance.allEmployees();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#allEmployees");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Employee&gt;**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="deleteEmployee"></a>
# **deleteEmployee**
> String deleteEmployee(empId)

Delete Employee

Employee should exist

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer empId = 56; // Integer | Id of the employee
    try {
      String result = apiInstance.deleteEmployee(empId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#deleteEmployee");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **empId** | **Integer**| Id of the employee | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="employeesAndDepartment"></a>
# **employeesAndDepartment**
> Object employeesAndDepartment(deptName)

Department employees

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    String deptName = "deptName_example"; // String | Department name
    try {
      Object result = apiInstance.employeesAndDepartment(deptName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#employeesAndDepartment");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **deptName** | **String**| Department name | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="employeesInBatch"></a>
# **employeesInBatch**
> Object employeesInBatch(pageNo, size, sortBy, order)

Employees in Batch

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer pageNo = 0; // Integer | Page number
    Integer size = 20; // Integer | Page size
    String sortBy = "salary"; // String | Column name by which employees need to be sorted
    String order = "asc"; // String | Id of the employee
    try {
      Object result = apiInstance.employeesInBatch(pageNo, size, sortBy, order);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#employeesInBatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **pageNo** | **Integer**| Page number | [optional] [default to 0] |
| **size** | **Integer**| Page size | [optional] [default to 20] |
| **sortBy** | **String**| Column name by which employees need to be sorted | [optional] [default to salary] |
| **order** | **String**| Id of the employee | [optional] [default to asc] |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="employeesInDepartment"></a>
# **employeesInDepartment**
> List&lt;Object&gt; employeesInDepartment()

Department employees count

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    try {
      List<Object> result = apiInstance.employeesInDepartment();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#employeesInDepartment");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;Object&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="employeesJoinedIn"></a>
# **employeesJoinedIn**
> Object employeesJoinedIn(key, value)

Employee joined recently

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    String key = "key_example"; // String | Search by year,month,week,day
    Integer value = 56; // Integer | value of year,month,week,day
    try {
      Object result = apiInstance.employeesJoinedIn(key, value);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#employeesJoinedIn");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **key** | **String**| Search by year,month,week,day | |
| **value** | **Integer**| value of year,month,week,day | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="employeesWithSalaryGreaterThan"></a>
# **employeesWithSalaryGreaterThan**
> Object employeesWithSalaryGreaterThan(amount)

Find Employees with salary greater than

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Double amount = 3.4D; // Double | Salary amount
    try {
      Object result = apiInstance.employeesWithSalaryGreaterThan(amount);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#employeesWithSalaryGreaterThan");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **amount** | **Double**| Salary amount | |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="incrementEmployeesSalary"></a>
# **incrementEmployeesSalary**
> String incrementEmployeesSalary(percent)

Update Employees salary

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Float percent = 3.4F; // Float | Increment salary percentage value
    try {
      String result = apiInstance.incrementEmployeesSalary(percent);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#incrementEmployeesSalary");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **percent** | **Float**| Increment salary percentage value | |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="removeEmployeeFromDepartment"></a>
# **removeEmployeeFromDepartment**
> Employee removeEmployeeFromDepartment(empId, deptId)

Remove Employee from department

Employee Id &amp; Department Id should be valid 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer empId = 56; // Integer | Id of the employee
    Integer deptId = 56; // Integer | Id of the department
    try {
      Employee result = apiInstance.removeEmployeeFromDepartment(empId, deptId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#removeEmployeeFromDepartment");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **empId** | **Integer**| Id of the employee | |
| **deptId** | **Integer**| Id of the department | |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="topThreeHighestPaidEmployees"></a>
# **topThreeHighestPaidEmployees**
> List&lt;SalariedEmployees&gt; topThreeHighestPaidEmployees()

Top Three salaried employees

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    try {
      List<SalariedEmployees> result = apiInstance.topThreeHighestPaidEmployees();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#topThreeHighestPaidEmployees");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;SalariedEmployees&gt;**](SalariedEmployees.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="transferEmployeeDepartment"></a>
# **transferEmployeeDepartment**
> Employee transferEmployeeDepartment(empId, fromDeptId, toDeptId)

Transfer Employees Department

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer empId = 56; // Integer | Id of the employee
    Integer fromDeptId = 56; // Integer | Id of the department from which employee to be transferred
    Integer toDeptId = 56; // Integer | Id of the department to which employee to be transferred
    try {
      Employee result = apiInstance.transferEmployeeDepartment(empId, fromDeptId, toDeptId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#transferEmployeeDepartment");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **empId** | **Integer**| Id of the employee | |
| **fromDeptId** | **Integer**| Id of the department from which employee to be transferred | |
| **toDeptId** | **Integer**| Id of the department to which employee to be transferred | |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="updateEmployee"></a>
# **updateEmployee**
> Employee updateEmployee(empId, employeeDto)

Update Employee

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.EmployeeControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    EmployeeControllerApi apiInstance = new EmployeeControllerApi(defaultClient);
    Integer empId = 56; // Integer | Id of the employee
    EmployeeDto employeeDto = new EmployeeDto(); // EmployeeDto | Employee details to update
    try {
      Employee result = apiInstance.updateEmployee(empId, employeeDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmployeeControllerApi#updateEmployee");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **empId** | **Integer**| Id of the employee | |
| **employeeDto** | [**EmployeeDto**](EmployeeDto.md)| Employee details to update | |

### Return type

[**Employee**](Employee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

