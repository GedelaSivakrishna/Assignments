# DepartmentControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addDepartment**](DepartmentControllerApi.md#addDepartment) | **POST** /department | Add Department |
| [**allDepartments**](DepartmentControllerApi.md#allDepartments) | **GET** /departments | List all Departments |
| [**deleteDepartment**](DepartmentControllerApi.md#deleteDepartment) | **DELETE** /department/{deptId} | Delete Department |


<a id="addDepartment"></a>
# **addDepartment**
> Department addDepartment(departmentDto)

Add Department

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DepartmentControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DepartmentControllerApi apiInstance = new DepartmentControllerApi(defaultClient);
    DepartmentDto departmentDto = new DepartmentDto(); // DepartmentDto | Department to create
    try {
      Department result = apiInstance.addDepartment(departmentDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DepartmentControllerApi#addDepartment");
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
| **departmentDto** | [**DepartmentDto**](DepartmentDto.md)| Department to create | |

### Return type

[**Department**](Department.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="allDepartments"></a>
# **allDepartments**
> List&lt;Department&gt; allDepartments()

List all Departments

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DepartmentControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DepartmentControllerApi apiInstance = new DepartmentControllerApi(defaultClient);
    try {
      List<Department> result = apiInstance.allDepartments();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DepartmentControllerApi#allDepartments");
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

[**List&lt;Department&gt;**](Department.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="deleteDepartment"></a>
# **deleteDepartment**
> String deleteDepartment(deptId)

Delete Department

Department must exist

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DepartmentControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DepartmentControllerApi apiInstance = new DepartmentControllerApi(defaultClient);
    Integer deptId = 56; // Integer | Id of the department to be deleted
    try {
      String result = apiInstance.deleteDepartment(deptId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DepartmentControllerApi#deleteDepartment");
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
| **deptId** | **Integer**| Id of the department to be deleted | |

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

