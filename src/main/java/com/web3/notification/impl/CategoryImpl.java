package com.web3.notification.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.common.base.Strings;
import com.web3.notification.exception.BadRequestException;
import com.web3.notification.exception.NotFoundException;
import com.web3.notification.model.CategoryModel;
import com.web3.notification.service.CategoryService;
import com.web3.notification.utility.HibernateFactory;

public class CategoryImpl implements CategoryService {

  @Override
  public String createNewCategory(CategoryModel categoryModel) {
    if (Strings.isNullOrEmpty(categoryModel.getName())) {
      throw new BadRequestException("Name is mandatory field");
    }
    if (Strings.isNullOrEmpty(categoryModel.getPriority())) {
      throw new BadRequestException("Priority is mandatory field");
    }
    Transaction transaction = null;
    try (Session session = HibernateFactory.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(categoryModel);
      transaction.commit();
    } catch (Exception error) {
      if (transaction != null) {
        transaction.rollback();
      }
    }
    return "Successfully created";
  }

  @Override
  public List<CategoryModel> getAllCategory() {
    try (Session session = HibernateFactory.getSessionFactory().openSession()) {
      return session.createQuery("from CategoryModel", CategoryModel.class).list();
    }
  }

  @Override
  public CategoryModel getCategoryById(int id) {
    Transaction transaction = null;
    CategoryModel resultObject = null;
    try (Session session = HibernateFactory.getSessionFactory().openSession()) {
      var query = session.createNativeQuery(
          "select (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) as bool from category04 where ID=:id");
      query.setParameter("id", id);
      boolean isCategoryFound = ((BigInteger)query.getSingleResult()).intValue() == 1 ? true : false;
      if (!isCategoryFound) {
        throw new NotFoundException("Data not found with requested id " + id);
      }
      transaction = session.beginTransaction();
      resultObject = session.get(CategoryModel.class, id);
      transaction.commit();
    } catch (Exception error) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw error;
    }
    return resultObject;

  }

  @Override
  public CategoryModel updateAnExistingCategory(CategoryModel notificationCategoryModel, int id) {
    Transaction transaction = null;
    CategoryModel categoryModel = null;
    try (Session session = HibernateFactory.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      var query = session.createNativeQuery(
          "select (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) as bool from category04 where ID=:id");
      query.setParameter("id", id);
      boolean isCategoryFound = ((BigInteger)query.getSingleResult()).intValue() == 1 ? true : false;
      if (!isCategoryFound) {
        throw new NotFoundException("Data not found with requested id " + id);
      }
      categoryModel = session.get(CategoryModel.class, id);
      categoryModel.setDescription(notificationCategoryModel.getDescription());
      categoryModel.setName(notificationCategoryModel.getName());
      categoryModel.setPriority(notificationCategoryModel.getPriority());
      categoryModel.setRateLimit(notificationCategoryModel.getRateLimit());
      categoryModel.setTtl(notificationCategoryModel.getTtl());
      session.update(categoryModel);
      transaction.commit();
    } catch (Exception error) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw error;
    }
    return categoryModel;
  }

  @Override
  public String deleteCategoryDetailsById(int id) {
    Transaction transaction = null;
    try (Session session = HibernateFactory.getSessionFactory().openSession()) {
      var query = session.createNativeQuery(
          "select (CASE WHEN COUNT(*) > 0 THEN true ELSE false END) as bool from category04 where ID=:id");
      query.setParameter("id", id);
      boolean isCategoryFound = ((BigInteger)query.getSingleResult()).intValue() == 1 ? true : false;
      if (!isCategoryFound) {
        throw new NotFoundException("Data not found with requested id " + id);
      }
      transaction = session.beginTransaction();
      CategoryModel categoryModelDel = session.get(CategoryModel.class, id);
      session.delete(categoryModelDel);
      transaction.commit();
    } catch (Exception error) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw error;
    }
    return "Successfully Deleted";

  }

}
