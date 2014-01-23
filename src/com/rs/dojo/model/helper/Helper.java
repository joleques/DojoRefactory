package com.rs.dojo.model.helper;

import java.lang.reflect.Field;

public class Helper {

	public static Integer getLength(final String name) {

		if (name.indexOf('.') > 0) {
			String entity = getEntity(name);
			String field = name.substring(name.indexOf('.') + 1);

			try {
				final Class<?> T = getClass(entity);
				final Length length = T.getDeclaredField(field).getAnnotation(Length.class);
				return (length != null) ? length.max() : null;
			} catch (Exception e) {
				// just ignore
			}
		}

		return 0;
	}

	

	private static Class<?> getClass(String entity) throws ClassNotFoundException {
		final Class<?> T = Class.forName("com.rs.dojo.model.helper.".concat(entity));
		return T;
	}

	private static String getEntity(final String name) {
		String entity = name.substring(0, name.indexOf('.'));
		entity = entity.substring(0, 1).toUpperCase().concat(entity.substring(1));
		return entity;
	}

	public static String getLabel(final String name) {
		if (name.indexOf('.') > 0) {
			String entity = getEntity(name);
			String field = name.substring(name.indexOf('.') + 1);

			try {
				final Class<?> T = getClass(entity);
				final Label label = T.getDeclaredField(field).getAnnotation(Label.class);
				return label.value();
			} catch (Exception e) {
				// just ignore
			}
		}

		return null;
	}

	public static String getValue(final String name, Object currentEntity) {
		if (name.indexOf('.') > 0) {
			String nameField = name.substring(name.indexOf('.') + 1);
			try {
				Class ftClass = currentEntity.getClass();
				Field field = ftClass.getDeclaredField(nameField);
				field.setAccessible(true);
				return (String) field.get(currentEntity);
			} catch (Exception e) {
				// just ignore
			}
		}
		return null;
	}

	public static String getViewTag(String name) {
		if (name.indexOf('.') > 0) {
			String entity = getEntity(name);
			String field = name.substring(name.indexOf('.') + 1);

			try {
				final Class<?> T = getClass(entity);
				final ViewTag viewTag = T.getDeclaredField(field).getAnnotation(ViewTag.class);
				return viewTag.value();
			} catch (Exception e) {
				// just ignore
			}
		}

		return null;
	}
}

