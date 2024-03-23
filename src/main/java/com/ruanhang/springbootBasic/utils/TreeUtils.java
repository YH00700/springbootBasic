package com.ruanhang.springbootBasic.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * TreeUtils
 * 需要使用 {@link TreeId}注解标记在树形结构id对应实体类的字段上
 * 需要使用 {@link TreeParentId}注解标记在树形结构id对应实体类的字段上
 * 需要使用 {@link TreeChildren}注解标记在树形结构id对应实体类的字段上
 *
 * @author ljw
 * @since 2020/3/11 10:42:42
 */
public class TreeUtils {

    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @return 转换后的树形结构
     */
    public static <T> List<T> toTree(@NotNull List<T> collection) {
        return (List<T>) toTree((Collection) collection);
    }

    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @return 转换后的树形结构
     */
    public static <T> Set<T> toTree(@NotNull Set<T> collection) {
        return (Set<T>) toTree((Collection) collection);
    }

    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @return 转换后的树形结构
     */
    public static <T> Collection<T> toTree(@NotNull Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        T t = collection.stream().findFirst().get();
        Field[] fields = t.getClass().getDeclaredFields();
        Field idField = null;
        Field parentIdField = null;
        Field childrenField = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TreeId.class)) {
                idField = field;
            } else if (field.isAnnotationPresent(TreeParentId.class)) {
                parentIdField = field;
            } else if (field.isAnnotationPresent(TreeChildren.class)) {
                childrenField = field;
            }
            if (Objects.nonNull(idField) && Objects.nonNull(parentIdField) && Objects.nonNull(childrenField)) {
                break;
            }
        }
        return toTree(collection, idField, parentIdField, childrenField);
    }

    /**
     * 集合转树结构
     *
     * @param collection    目标集合
     * @param idField       节点编号字段对象
     * @param parentIdField 父节点编号字段对象
     * @param childrenField 子节点集合字段对象
     * @return 转换后的树形结构
     */
    private static <T> Collection<T> toTree(@NotNull Collection<T> collection, Field idField, Field parentIdField, Field childrenField) {
        // 校验参数
        Assert.notNull(idField, "id字段为空");
        Assert.notNull(parentIdField, "parentField字段为空");
        Assert.notNull(childrenField, "childrenField字段为空");
        Assert.notEmpty(collection, "collection不允许为空");

        Class<? extends Collection> collectionClass = collection.getClass();
        Constructor<? extends Collection> constructor;
        try {
            constructor = collectionClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("无法获取" + collectionClass.getName() + "的构造方法，请检查collection对应类或构造器的访问策略", e);
        }

        try {
            // 初始结果集, 结果集集合类型和参数集合类型保持一致
            Collection<T> roots = constructor.newInstance();

            // 设置为可访问
            idField.setAccessible(true);
            parentIdField.setAccessible(true);
            childrenField.setAccessible(true);

            // 找出所有的根节点，把数据按照parentId进行分组处理成map
            Map<Object, Collection<T>> groupMap = new HashMap<>();
            Set<Object> idSet = new HashSet<>();
            Collection<T> itemGroupList;
            Object id;
            Object parentId;
            for (T c : collection) {
                id = idField.get(c);
                parentId = parentIdField.get(c);
                // 处理id集合
                if (Objects.nonNull(id)) {
                    idSet.add(id);
                }
                // 分组处理
                if (Objects.nonNull(parentId)) {
                    if (!groupMap.containsKey(parentId) || Objects.isNull(groupMap.get(parentId))) {
                        itemGroupList = constructor.newInstance();
                        groupMap.put(parentId, itemGroupList);
                    } else {
                        itemGroupList = groupMap.get(parentId);
                    }
                    itemGroupList.add(c);
                }
            }

            // 设置子节点集合数据
            for (T c : collection) {
                id = idField.get(c);
                parentId = parentIdField.get(c);
                // 判断是否是根节点
                if (isRootNode(parentId) || !idSet.contains(parentId)) {
                    roots.add(c);
                }
                // 获取当前节点所有子节点
                if (Objects.nonNull(id)) {
                    itemGroupList = groupMap.get(id);
                    // 设置子节点到父节点children属性
                    childrenField.set(c, itemGroupList);
                }
            }

            // 关闭可访问
            idField.setAccessible(false);
            parentIdField.setAccessible(false);
            childrenField.setAccessible(false);

            return roots;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否是根节点：父节点编号为空或为 0, 则认为是根节点。
     *
     * @param parentId 父节点编号
     * @return 是否是根节点
     */
    private static boolean isRootNode(Object parentId) {
        boolean flag = false;
        if (parentId == null) {
            flag = true;
        } else if (parentId instanceof String && (StringUtils.isEmpty(parentId) || "0".equals(parentId))) {
            flag = true;
        } else if (parentId instanceof Integer && "0".equals(parentId)) {
            flag = true;
        } else if (parentId instanceof Long && "0L".equals(parentId)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 转树结构转扁平集合
     *
     * @param collection 目标集合
     * @return 转换后的树形结构
     */
    public static <T> List<T> openTree(@NotNull List<T> collection) {
        return (List<T>) openTree((Collection<T>) collection);
    }

    /**
     * 转树结构转扁平集合
     *
     * @param collection 目标集合
     * @return 转换后的树形结构
     */
    public static <T> Set<T> openTree(@NotNull Set<T> collection) {
        return (Set<T>) openTree((Collection<T>) collection);
    }

    /**
     * 转树结构转扁平集合
     *
     * @param collection 目标集合
     * @return 转换后的树形结构
     */
    public static <T> Collection<T> openTree(@NotNull Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        T t = collection.stream().findFirst().get();
        Field[] fields = t.getClass().getDeclaredFields();
        Field childrenField = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TreeChildren.class)) {
                childrenField = field;
                break;
            }
        }
        return openTree(collection, childrenField);
    }

    /**
     * 树形结构转扁平集合
     *
     * @param collection    目标集合
     * @param childrenField 子节点集合字段对象
     * @return 转换后的扁平数据集合
     */
    private static <T> Collection<T> openTree(@NotNull Collection<T> collection, Field childrenField) {
        // 校验参数
        Assert.notNull(childrenField, "childrenField字段为空");
        Assert.notEmpty(collection, "collection不允许为空");

        try {
            Class<? extends Collection> collectionClass = collection.getClass();
            // 初始结果集, 结果集集合类型和参数集合类型保持一致
            Collection<T> resultList = collectionClass.newInstance();

            // 设置为可访问
            childrenField.setAccessible(true);

            // 遍历根节点, 依次添加子节点
            openTreeList(collection, childrenField, resultList);

            // 关闭可访问
            childrenField.setAccessible(false);

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 递归组装扁平集合数据
     */
    private static <T> void openTreeList(@NotNull Collection<T> collection, @NotNull Field childrenField, @NotNull Collection<T> resultList) throws IllegalAccessException {
        for (T item : collection) {
            resultList.add(item);
            Collection<T> childrenList = (Collection<T>) childrenField.get(item);
            if (childrenList != null && childrenList.size() > 0) {
                openTreeList(childrenList, childrenField, resultList);
            }
        }
    }

}
